package active.since93.pdfreader;

import java.util.Locale;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView textView;
	private Button speakBtn, btnStop;
	TextToSpeech textToSpeech;
	String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView);
		speakBtn = (Button) findViewById(R.id.btnSpeak);
		btnStop = (Button) findViewById(R.id.btnStop);

		// Change samplepdf with your pdf file name.
		String readfilename = "samplepdf";
		FileOperations fop = new FileOperations();
		text = fop.read(readfilename);
		textView.setText(text);

		speakBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(text != null) {
					textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});

		btnStop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textToSpeech.stop();
			}
		});

		textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if(status != TextToSpeech.ERROR) {
					// Set language
					textToSpeech.setLanguage(Locale.UK);
				}
			}
		});
	}
}