package it.damianogiusti.giustidamiano;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by damiano on 27/05/16.
 */
public class DialogReset extends DialogFragment {

    public interface IDialogResetListener {
        void reset(boolean mustReset);
    }

    IDialogResetListener listener = new IDialogResetListener() {
        @Override
        public void reset(boolean mustReset) {
            // dummy init
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDialogResetListener) {
            listener = (IDialogResetListener)activity;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage("Vuoi azzerare?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.reset(true);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.reset(false);
                    }
                });

        return builder.create();
    }
}
