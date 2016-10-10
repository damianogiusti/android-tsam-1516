package it.damianogiusti.giustidamiano;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

/**
 * Created by damiano on 27/05/16.
 */
public class DialogRaddoppia extends DialogFragment{

    private static final String TAG = "DialogRaddoppia";

    public interface IDialogRaddoppiaListener {
        void raddoppia(boolean mustDouble);
    }

    private IDialogRaddoppiaListener listener = new IDialogRaddoppiaListener() {
        @Override
        public void raddoppia(boolean mustDouble) {
            // dummmy init
            Log.d(TAG, "raddoppia: dummy init");
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDialogRaddoppiaListener) {
            listener = (IDialogRaddoppiaListener)activity;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage("Vuoi raddoppiare?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.raddoppia(true);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.raddoppia(false);
                    }
                });
        return builder.create();
    }
}
