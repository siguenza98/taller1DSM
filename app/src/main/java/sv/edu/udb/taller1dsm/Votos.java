package sv.edu.udb.taller1dsm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Votos extends Fragment {
    private EditText num1, total1, porcentaje1,num2, total2, porcentaje2,num3, total3, porcentaje3,num4, total4, porcentaje4;
    private EditText inputVoto;
    private Button btnContar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_votos, container, false);
        num1 = (EditText)view.findViewById(R.id.diputadoNum1);
        total1 = (EditText)view.findViewById(R.id.diputadoTotal1);
        porcentaje1 = (EditText)view.findViewById(R.id.diputadoPorcentaje1);

        num2 = (EditText)view.findViewById(R.id.diputadoNum2);
        total2 = (EditText)view.findViewById(R.id.diputadoTotal2);
        porcentaje2 = (EditText)view.findViewById(R.id.diputadoPorcentaje2);

        num3 = (EditText)view.findViewById(R.id.diputadoNum3);
        total3 = (EditText)view.findViewById(R.id.diputadoTotal3);
        porcentaje3 = (EditText)view.findViewById(R.id.diputadoPorcentaje3);

        num4 = (EditText)view.findViewById(R.id.diputadoNum4);
        total4 = (EditText)view.findViewById(R.id.diputadoTotal4);
        porcentaje4 = (EditText)view.findViewById(R.id.diputadoPorcentaje4);

        inputVoto = (EditText)view.findViewById(R.id.inputVoto);

        btnContar = (Button)view.findViewById(R.id.btnContar);
        btnContar.setOnClickListener(contarListener);
        return view;
    }

    private View.OnClickListener contarListener = new View.OnClickListener() {
        public void onClick(View v) {
            contar();
        }
    };

    public void contar(){
        Integer votos1 = 0; Double percent1 = 0.0;
        Integer votos2 = 0; Double percent2 = 0.0;
        Integer votos3 = 0; Double percent3 = 0.0;
        Integer votos4 = 0; Double percent4 = 0.0;
        Integer total = 0;

        String votos = inputVoto.getText().toString();
        if(votos.isEmpty()){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                    "!Error! Debe de ingresar el listado de votos.",
                    Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        String[] listadoVotos = TextUtils.split(votos, ",");
        int n = listadoVotos.length;
        for(int i=0; i<n;i++){
            switch (listadoVotos[i]){
                case "1":
                    votos1 += 1;
                    break;
                case "2":
                    votos2 += 1;
                    break;
                case "3":
                    votos3 += 1;
                    break;
                case "4":
                    votos4 += 1;
                    break;
                default:
                    break;
            }
            total += 1;
        }
        DecimalFormat formato = new DecimalFormat("00.00");

        percent1 = Double.valueOf(votos1)/total;
        percent2 = Double.valueOf(votos2)/total;
        percent3 = Double.valueOf(votos3)/total;
        percent4 = Double.valueOf(votos4)/total;

        num1.setText("Candidato 1"); total1.setText(votos1.toString()); porcentaje1.setText(formato.format(percent1*100).toString()+"%");
        num2.setText("Candidato 2"); total2.setText(votos2.toString()); porcentaje2.setText(formato.format(percent2*100).toString()+"%");
        num3.setText("Candidato 3"); total3.setText(votos3.toString()); porcentaje3.setText(formato.format(percent3*100).toString()+"%");
        num4.setText("Candidato 4"); total4.setText(votos4.toString()); porcentaje4.setText(formato.format(percent4*100).toString()+"%");
    }
}