package it.damianogiusti.giustidamiano;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment {

    private static final String TAG = "ButtonFragment";

    private static final String KEY_CONTATORE_FOR_BUNDLE = "keycontatoreforbundle";

    private TextView txtContatore;
    private int contatore;

    public interface IButtonFragmentListener {
        void onPiuPressed();

        void onMenoPressed();

        void onResetPressed();

        void onRaddoppiaPressed();

        void onTriplicaPressed();
    }

    private IButtonFragmentListener listener = new IButtonFragmentListener() {
        @Override
        public void onPiuPressed() {

        }

        @Override
        public void onMenoPressed() {

        }

        @Override
        public void onResetPressed() {

        }

        @Override
        public void onRaddoppiaPressed() {

        }

        @Override
        public void onTriplicaPressed() {

        }
    };

    public ButtonFragment() {
        // Required empty public constructor
    }

    public static ButtonFragment newInstance(int contatore) {
        ButtonFragment buttonFragment = new ButtonFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_CONTATORE_FOR_BUNDLE, contatore);
        buttonFragment.setArguments(args);
        return buttonFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IButtonFragmentListener) {
            listener = (IButtonFragmentListener) activity;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        txtContatore = (TextView) view.findViewById(R.id.txtContatore);

        contatore = getArguments().getInt(KEY_CONTATORE_FOR_BUNDLE);
        if (savedInstanceState != null) {
            contatore = savedInstanceState.getInt(KEY_CONTATORE_FOR_BUNDLE);
        }

        Button btnPiu = (Button) view.findViewById(R.id.btnPiu);
        btnPiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPiuPressed();
            }
        });

        Button btnMeno = (Button) view.findViewById(R.id.btnMeno);
        btnMeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMenoPressed();
            }
        });

        Button btnReset = (Button) view.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReset();
            }
        });

        Button btnRaddoppia = (Button) view.findViewById(R.id.btnRaddoppia);
        btnRaddoppia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRaddoppia();
            }
        });

        Button btnTriplica = (Button) view.findViewById(R.id.btnTriplica);
        btnTriplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTriplica();
            }
        });

        Log.d(TAG, "onCreateView: ");
        Log.d(TAG,""+ contatore);
        updateGUI(contatore);
        return view;
    }

    public void updateGUI(int contatore) {
        this.contatore = contatore;
        txtContatore.setText("" + this.contatore);
    }

    private void onReset() {
        listener.onResetPressed();
    }

    private void onRaddoppia() {
        listener.onRaddoppiaPressed();
    }

    private void onTriplica() {
        listener.onTriplicaPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTATORE_FOR_BUNDLE, contatore);
    }
}
