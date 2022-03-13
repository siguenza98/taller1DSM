package sv.edu.udb.taller1dsm;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;


public class Cuadratica extends Fragment {
    private EditText inputA, inputB, inputC, inputX1, inputX2;
    private Button btnCalcular;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuadratica, container, false);
        inputA = (EditText)view.findViewById(R.id.inputA);
        inputB = (EditText)view.findViewById(R.id.inputB);
        inputC = (EditText)view.findViewById(R.id.inputC);
        inputX1 = (EditText)view.findViewById(R.id.inputX1);
        inputX2 = (EditText)view.findViewById(R.id.inputX2);

        btnCalcular = (Button)view.findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(calcularListener);
        return view;
    }

    private View.OnClickListener calcularListener = new View.OnClickListener() {
        public void onClick(View v) {
            calcular();
        }
    };

    public void calcular(){
        String strA = inputA.getText().toString();
        String strB = inputB.getText().toString();
        String strC = inputC.getText().toString();
        Double valorA, valorB, valorC;

        if(strA.isEmpty() || strB.isEmpty()|| strC.isEmpty()){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                    "!Error! Debe de ingresar todos los valores.",
                    Toast.LENGTH_LONG);
            toast.show();
            return;
        }else{
            valorA = Double.parseDouble(strA);
            valorB = Double.parseDouble(strB);
            valorC = Double.parseDouble(strC);
        }

        double determinante = (valorB*valorB)-4*(valorA*valorC);

        if(determinante < 0){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                    "!Error! El determinante es menor que cero.",
                    Toast.LENGTH_LONG);
            toast.show();
        }else{
            DecimalFormat formato = new DecimalFormat("00.00");

            Double x1 =  (-1*valorB + Math.sqrt(determinante))/(2*valorA);
            Double x2 =  (-1*valorB - Math.sqrt(determinante))/(2*valorA);
            inputX1.setText(formato.format(x1).toString());
            inputX2.setText(formato.format(x2).toString());
        }

    }
}