package com.filipkesteli.draganddrop;


import android.os.Bundle;
import android.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DropzoneFragment extends Fragment {

    private TextView dropMessage;
    private View dropTarget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Ovaj inflater ce inflateat citavi View
        View v = inflater.inflate(R.layout.fragment_dropzone, container, false);
        //sad su i dropTarget i dropMessage naloadani
        init(v);
        return v;
    }

    private void init(View v) {
        dropMessage = (TextView) v.findViewById(R.id.dropMessage);
        dropTarget = v.findViewById(R.id.dropTarget);

        dropTarget.setOnDragListener(new View.OnDragListener() {

            private int count = 0; //Unutarnji counter:

            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        String message = (++count) + "";
                        dropMessage.setText(message);
                }

                return true;
            }
        });
    }
}
