/**
 * 2014-08-01 K.OHWADA
 */ 

package jp.ohwada.android.usbserialterminal1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * MainActivity
 */     
public class MainActivity extends CommonActivity {
            
	// UI
	private EditText mEditTextBaudrate;
	private EditText mEditTextSend;
	private ArrayAdapter<String> mAdapter;

	/**
	 * === onCreate ===
	 */     
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		initManager();
		initView();

		mEditTextBaudrate = (EditText) findViewById( R.id.EditText_baudrate);
		mEditTextSend = (EditText) findViewById( R.id.EditText_send );

		Button btnBaudrate = (Button) findViewById( R.id.Button_baudrate );
		btnBaudrate.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View view ) {
				execBaudrate();
			}
		});	
						
		Button btnSend = (Button) findViewById( R.id.Button_send );
		btnSend.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View view ) {
				execSend();
			}
		});	

		ListView lv = (ListView) findViewById( R.id.ListView );
		mAdapter = new ArrayAdapter<String>( 
			this, R.layout.report, R.id.TextView_report );
		lv.setAdapter( mAdapter );

	}

	/**
	 * execBaudrate
	 */
	private void execBaudrate() {
		String str = mEditTextBaudrate.getText().toString();
		if ( str.length() > 0 ) {
			int baudrate = Integer.parseInt( str );
			boolean ret = setBaudrate( baudrate );
			if ( ret ) {
				String msg = getString( R.string.msg_baudrate ) + SPACE +  baudrate;
				toast_show( msg );
			} else {
				toast_show( R.string.msg_not_set_baudrate );
			}
		} else {
			toast_show( R.string.msg_enter_baudrate );
		}
	}

	/**
	 * execSend
	 */
	private void execSend() {
		String str = mEditTextSend.getText().toString();
		if ( str.length() > 0 ) {
			send( str );
		} else {
			toast_show( R.string.msg_enter_text );
		}
	}

	/**
	 * execRecieve
	 * @param byte[] bytes
	 */	
	protected void execRecieve( byte[] bytes ) {
	    String str = bytesToString( bytes );
		if ( str != null ) {
			mAdapter.add( str );
		}
	}

}
