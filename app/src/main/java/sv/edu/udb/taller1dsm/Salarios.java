package sv.edu.udb.taller1dsm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

import javax.security.auth.Destroyable;

public class Salarios extends Fragment {
    private EditText txtNombre1,txtNombre2,txtNombre3, txtApellidos1,txtApellidos2,txtApellidos3,txtHoras1,txtHoras2,txtHoras3;
    private Spinner txtCargo1,txtCargo2,txtCargo3;
    private Button btnCalcular;

    private EditText txtNombreR1,txtNombreR2,txtNombreR3, txtDescuento1,txtDescuento2,txtDescuento3,txtNeto1,txtNeto2,txtNeto3;
    private EditText txtMayor, txtMenor, txtMas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salarios, container, false);
        txtNombre1 = (EditText)view.findViewById(R.id.txtNombre1);
        txtNombre2 = (EditText)view.findViewById(R.id.txtNombre2);
        txtNombre3 = (EditText)view.findViewById(R.id.txtNombre3);

        txtApellidos1 = (EditText)view.findViewById(R.id.txtApellidos1);
        txtApellidos2 = (EditText)view.findViewById(R.id.txtApellidos2);
        txtApellidos3 = (EditText)view.findViewById(R.id.txtApellidos3);

        txtHoras1 = (EditText)view.findViewById(R.id.txtHoras1);
        txtHoras2 = (EditText)view.findViewById(R.id.txtHoras2);
        txtHoras3 = (EditText)view.findViewById(R.id.txtHoras3);

        txtCargo1 = (Spinner)view.findViewById(R.id.txtCargo1);
        txtCargo2 = (Spinner)view.findViewById(R.id.txtCargo2);
        txtCargo3 = (Spinner)view.findViewById(R.id.txtCargo3);
        String[] arraySpinner = new String[] {
                "Empleado", "Secretaria", "Asistente", "Gerente"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        txtCargo1.setAdapter(adapter1);
        txtCargo2.setAdapter(adapter1);
        txtCargo3.setAdapter(adapter1);

        btnCalcular = (Button)view.findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(contarListener);

        txtNombreR1 = (EditText)view.findViewById(R.id.txtNombreR1);
        txtNombreR2 = (EditText)view.findViewById(R.id.txtNombreR2);
        txtNombreR3 = (EditText)view.findViewById(R.id.txtNombreR3);

        txtDescuento1 = (EditText)view.findViewById(R.id.txtDescuento1);
        txtDescuento2 = (EditText)view.findViewById(R.id.txtDescuento2);
        txtDescuento3 = (EditText)view.findViewById(R.id.txtDescuento3);

        txtNeto1 = (EditText)view.findViewById(R.id.txtNeto1);
        txtNeto2 = (EditText)view.findViewById(R.id.txtNeto2);
        txtNeto3 = (EditText)view.findViewById(R.id.txtNeto3);

        txtMayor = (EditText)view.findViewById(R.id.txtMayor);
        txtMenor = (EditText)view.findViewById(R.id.txtMenor);

        txtMas= (EditText)view.findViewById(R.id.txtMas);

        return view;
    }

    private View.OnClickListener contarListener = new View.OnClickListener() {
        public void onClick(View v) {
            calcular();
        }
    };

    private void calcular(){
        EditText[] nombres = {txtNombre1, txtNombre2, txtNombre3};
        EditText[] apellidos = {txtApellidos1, txtApellidos2, txtApellidos3};
        EditText[] horas = {txtHoras1, txtHoras2, txtHoras3};
        Spinner[] cargos = {txtCargo1, txtCargo2, txtCargo3};

        EditText[] nombresR = {txtNombreR1, txtNombreR2, txtNombreR3};
        EditText[] descuentos = {txtDescuento1, txtDescuento2, txtDescuento3};
        EditText[] netos = {txtNeto1, txtNeto2, txtNeto3};

        Double mayor = -1.00;
        String nombresMayor = "";

        Double menor = 999999999999999.99;
        String nombresMenor = "";

        Boolean hayBono = true;
        String nombresMasQue = "";

        for(int i =0; i < 3; i++){
            String nombreCompleto = nombres[i].getText().toString()+" "+apellidos[i].getText().toString();
            String horasString = horas[i].getText().toString();
            String cargoRealizado = cargos[i].getSelectedItem().toString();
            Double salario = 0.0;
            Double descuento = 0.0;

            if(nombreCompleto.isEmpty() || horasString.isEmpty() || cargoRealizado.isEmpty()){
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "!Error! Debe de ingresar todos los datos de los empleados.",
                        Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            Integer horasTrabajadas = Integer.parseInt(horasString);

            if(horasTrabajadas > 160){
                Integer horasExtra = horasTrabajadas - 160;
                salario += 11.50 * horasExtra;
                horasTrabajadas = horasTrabajadas - horasExtra;
            }

            salario += 9.75 * horasTrabajadas;

            descuento = salario * 0.2213; //0.25 + 0.688 + 0.1
            salario = salario - descuento;

            if(cargos[0].getSelectedItem().toString() == "Gerente" && cargos[1].getSelectedItem().toString() == "Asistente" && cargos[2].getSelectedItem().toString() == "Secretaria"){
                hayBono = false;
            }

            if(hayBono){
                switch (cargoRealizado){
                    case "Gerente":
                        salario = salario * 1.1;
                        break;
                    case "Asistente":
                        salario = salario * 1.05;
                        break;
                    case "Secretaria":
                        salario = salario * 1.03;
                        break;
                    case "Empleado":
                        salario = salario * 1.02;
                        break;
                }
            }

            DecimalFormat formato = new DecimalFormat("00.00");

            nombresR[i].setText(nombreCompleto);
            descuentos[i].setText("-$"+ formato.format(descuento).toString());
            netos[i].setText("$" + formato.format(salario).toString());

            if(mayor < salario){
                mayor = salario;
                nombresMayor = nombreCompleto;
            }

            if(salario < menor){
                menor = salario;
                nombresMenor = nombreCompleto;
            }

            if(salario > 300){
                nombresMasQue = nombresMasQue + " " + nombreCompleto + ",";
            }
        }

        txtMayor.setText("Mayor: " +nombresMayor);
        txtMenor.setText("Menor: " +nombresMenor);
        txtMas.setText(nombresMasQue);


        if(!hayBono){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                    "No hubieron bonos.",
                    Toast.LENGTH_LONG);
            toast.show();
        }

    }
}