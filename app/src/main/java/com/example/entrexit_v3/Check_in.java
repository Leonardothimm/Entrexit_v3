package com.example.entrexit_v3;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.entrexit_v3.MainActivity;
import com.example.entrexit_v3.R;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class Check_in extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    Button myButton;
    ToggleButton tglReadWrite;
    TextView txtTagContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        myButton = (Button) findViewById(R.id.checkIntBtn);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC available", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "NFC not available in simulator", Toast.LENGTH_LONG).show();
        }
    }
        @Override
        protected void onResume(){
            if(nfcAdapter!= null) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(intent.FLAG_RECEIVER_REPLACE_PENDING);

                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                IntentFilter[] intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
            }
            super.onResume();
        }
    @Override
    protected void onPause() {
        if(nfcAdapter!= null) {
            nfcAdapter.disableForegroundDispatch(this);


        }
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent.hasExtra(nfcAdapter.EXTRA_TAG)){

            Toast.makeText(this, "NFC Intent!", Toast.LENGTH_SHORT).show();

            if(tglReadWrite.isChecked()){ //red tag must be associated to where the info is coming from AKA button, etc

                Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

                if( parcelables != null && parcelables.length >0 ){

                    readTextFromMessage((NdefMessage)parcelables[0]);

                }else{
                    Toast.makeText(this, "No NFC messages found!", Toast.LENGTH_SHORT).show();
                }

            }else{
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

                NdefMessage ndefMessage = createNdefMessage("My string content!");//here what we want to store in the tag

                writeNdfMessage(tag, ndefMessage);
            }
        }
    }

    private void readTextFromMessage(NdefMessage ndefMessage) {

        NdefRecord[] ndefRecords =ndefMessage.getRecords();

        if(ndefRecords != null &&  ndefRecords.length> 0){

            NdefRecord ndefRecord = ndefRecords[0];

            String tagContent = getTextFromNdefRecord(ndefRecord);

            txtTagContent.setText(tagContent); //associated with a text view point (should we change it to the user's username?)

        }else{
            Toast.makeText(this, "No NFC records found!", Toast.LENGTH_SHORT).show();
        }
    }
    private void enableForegroundDispatchSystem(){

        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[] {};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);

    }
    private void disableForegroundDispatchSystem(){

        nfcAdapter.disableForegroundDispatch(this);

    }

    private void formatTag(Tag tag, NdefMessage ndefMessage){
        try {

            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if(ndefFormatable == null){

                Toast.makeText(this, "Tag is not ndef formatable", Toast.LENGTH_SHORT).show();
                ndefFormatable.connect();
                ndefFormatable.format(ndefMessage);
                ndefFormatable.close();
            }

        }catch(Exception e)
        {
            Log.e("formatTag", e.getMessage());
        }

    }
    private void writeNdfMessage(Tag tag, NdefMessage ndefMessage){
        try {

            if(tag == null){

                Toast.makeText(this, "tag object can not be null", Toast.LENGTH_SHORT).show();
                return;

            }

            Ndef ndef = Ndef.get(tag);

            if(ndef == null){
                formatTag(tag, ndefMessage);
            }else{
                ndef.connect();
                if(!ndef.isWritable()){
                    Toast.makeText(this, "tag is not writable", Toast.LENGTH_SHORT).show();
                    ndef.close();
                    return;
                }
                ndef.writeNdefMessage(ndefMessage);
                ndef.close();
                Toast.makeText(this, "tag written", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e)
        {
            Log.e("writeNdeMessage", e.getMessage());
        }
    }
    private NdefRecord createTextRecord(String content){
        try{
            byte[] language;
            language= Locale.getDefault().getLanguage().getBytes("UTF-8");

            final byte[] text = content.getBytes("UTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1+ languageSize + textLength);

            payload.write (languageSize & 0x1F);
            payload.write(language, 0, languageSize);
            payload.write(text, 0, textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());

        }catch (UnsupportedEncodingException e){
            Log.e("createTextRecord", e.getMessage());
        }
        return null;
    }
    private NdefMessage createNdefMessage(String content){

        //here input for writing.

        NdefRecord ndefRecord = createTextRecord(content);
        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ndefRecord});

        return ndefMessage;
    }

    public void tglReadWriteOnClick(View view){
        //must add a view to button in layout for specific manual input.
        //variableWithValue.setText("");
    }

    public String getTextFromNdefRecord (NdefRecord ndefRecord){

        String tagContent = null;
        try{
            byte[] payload = ndefRecord.getPayload();
            String textEncoding =((payload[0]& 128 )==0) ? "UTF-8" : "UTF-16" ;
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1,
                    payload.length - languageSize - 1, textEncoding);
        }catch(UnsupportedEncodingException e){
            Log.e("getTextFromNdefRecord", e.getMessage(), e);
        }
        return tagContent;
    }

}


