package it.damianogiusti.giustidamiano;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        ButtonFragment.IButtonFragmentListener,
        DialogReset.IDialogResetListener,
        DialogRaddoppia.IDialogRaddoppiaListener {

    private static final String TAG_DIALOG_RESET = "tagdialogreset";
    private static final String TAG_DIALOG_RADDOPPIA = "tagdialograddoppia";
    private static final String TAG_BUTTON_FRAGMENT = "tagbtnfragment";
    private static final String TAG_TRIPLICA_FRAGMENT = "tagtriplicafragment";

    private static final String KEY_CONTATORE_FOR_BUNDLE = "keycontatoresaveinstancestate";
    private ButtonFragment buttonFragment;
    private int contatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contatore = 0;
        if (savedInstanceState != null) {
            contatore = savedInstanceState.getInt(KEY_CONTATORE_FOR_BUNDLE, 0);
        }

        buttonFragment = ((ButtonFragment) getSupportFragmentManager().findFragmentByTag(TAG_BUTTON_FRAGMENT));
        if (buttonFragment == null) {
            buttonFragment = ButtonFragment.newInstance(contatore);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_layout, buttonFragment, TAG_BUTTON_FRAGMENT)
                    .commit();
        }
    }



    @Override
    public void onPiuPressed() {
        contatore++;
        buttonFragment.updateGUI(contatore);
    }

    @Override
    public void onMenoPressed() {
        if (contatore > 0)
            contatore--;
        buttonFragment.updateGUI(contatore);
    }

    @Override
    public void onResetPressed() {
        DialogReset dialogReset = new DialogReset();
        dialogReset.show(getSupportFragmentManager(), TAG_DIALOG_RESET);
    }

    @Override
    public void onRaddoppiaPressed() {
        DialogRaddoppia dialogRaddoppia = new DialogRaddoppia();
        dialogRaddoppia.show(getSupportFragmentManager(), TAG_DIALOG_RADDOPPIA);
    }

    @Override
    public void onTriplicaPressed() {
        TriplicaFragment triplicaFragment =
                (TriplicaFragment) getSupportFragmentManager().findFragmentByTag(TAG_TRIPLICA_FRAGMENT);
        if (triplicaFragment == null) {
            triplicaFragment = TriplicaFragment.newInstance(contatore);
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_layout, triplicaFragment)
                    .commit();
        }
    }

    @Override
    public void reset(boolean mustReset) {
        if (mustReset) {
            contatore = 0;
            buttonFragment.updateGUI(contatore);
        }
    }

    @Override
    public void raddoppia(boolean mustDouble) {
        if (mustDouble) {
            contatore *= 2;
            buttonFragment.updateGUI(contatore);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTATORE_FOR_BUNDLE, contatore);
    }
}
