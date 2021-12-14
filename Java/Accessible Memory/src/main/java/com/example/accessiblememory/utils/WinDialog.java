package com.example.accessiblememory.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.accessiblememory.R;
import com.example.accessiblememory.contracts.IGamePresenter;
import com.example.accessiblememory.views.MainActivity;

public class WinDialog extends DialogFragment {

    IGamePresenter gamePresenter;

    public WinDialog(IGamePresenter gamePresenter) {
        this.gamePresenter = gamePresenter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.DialogStyle)
                .setMessage(R.string.winDialogText)
                .setPositiveButton(R.string.winDialogPositiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        gamePresenter.newGame();
                    }
                })
                .setNegativeButton(R.string.winDialogNegativeButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                }).show();
        TextView textView = dialog.findViewById(android.R.id.message);
        textView.setTextSize(40);


        return dialog;

    }

}
