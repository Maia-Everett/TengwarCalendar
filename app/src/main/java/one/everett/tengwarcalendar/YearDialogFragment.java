package one.everett.tengwarcalendar;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import one.everett.tengwar.Numerals;
import one.everett.tengwarcalendar.databinding.FragmentYearDialogBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class YearDialogFragment extends DialogFragment {
    public static final String EXTRA_YEAR = YearDialogFragment.class.getPackage().getName() + ".year";

    public static YearDialogFragment newInstance(int year) {
        YearDialogFragment fragment = new YearDialogFragment();

        Bundle args = new Bundle();
        args.putInt(EXTRA_YEAR, year);
        fragment.setArguments(args);

        return fragment;
    }

    private Callbacks callbacks;
    private FragmentYearDialogBinding ui;
    private int year;

    public YearDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            year = getArguments().getInt(EXTRA_YEAR);
        } else {
            year = savedInstanceState.getInt(EXTRA_YEAR);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callbacks = (Callbacks) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ui = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_year_dialog, null, false);
        ui.yearView.setTypeface(Assets.get(getActivity()).getRegularFont());
        update();

        ui.plusButton.setOnClickListener(view -> {
            year++;
            update();
        });

        ui.minusButton.setOnClickListener(view -> {
            year--;
            update();
        });

        return new AlertDialog.Builder(getActivity())
                .setView(ui.getRoot())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    callbacks.onYearSelected(year);
                    dialog.dismiss();
                })
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                .create();
    }

    private void update() {
        ui.yearView.setText(Numerals.toTengwarString(year));
    }

    public interface Callbacks {
        void onYearSelected(int year);
    }
}
