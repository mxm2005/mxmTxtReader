package com.example.mxmtxtreader.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;

public class YesNoDialog extends DialogFragment
{
    public YesNoDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                }
            })
            .create();
    }
}
