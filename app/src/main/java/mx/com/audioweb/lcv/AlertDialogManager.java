package mx.com.audioweb.lcv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Juan Acosta on 8/25/2014.
 */
public class AlertDialogManager {
    @SuppressWarnings("deprecation")
    public void showAlertDialog(Context context, String title, String message, Boolean status){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(final DialogInterface dialog, final int which) {
                // TODO Auto-generated method stub
            }
        });

        alertDialog.show();
    }
}
