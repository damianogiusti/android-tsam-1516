package it.damianogiusti.giustidamiano;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by damiano on 27/05/16.
 */
public class TriplicaFragment extends Fragment{

    private static final String KEY_CONTATORE_FOR_BUNDLE = "keycontatoreforbundle";

    private TextView txtTriplicato;
    private int contatore;

    public static TriplicaFragment newInstance(int contatore) {
        TriplicaFragment triplicaFragment = new TriplicaFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_CONTATORE_FOR_BUNDLE, contatore);
        triplicaFragment.setArguments(args);
        return triplicaFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_triplica, container, false);

        txtTriplicato = (TextView) view.findViewById(R.id.txtTriplicato);

        if (savedInstanceState != null) {
            contatore = savedInstanceState.getInt(KEY_CONTATORE_FOR_BUNDLE, 0);
        } else {
            contatore = getArguments().getInt(KEY_CONTATORE_FOR_BUNDLE, 0);
            contatore *= 3;
        }

        updateGUI();
        return view;
    }

    private void updateGUI() {
        txtTriplicato.setText("" + contatore);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTATORE_FOR_BUNDLE, contatore);
    }
}
