package co.edu.unipiloto.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {

    private EditText et_Name, et_LastName, et_user, et_Email, et_Password2,et_Password;
    private Spinner et_rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        et_Name = (EditText)findViewById(R.id.txt_FirstName);
        et_LastName = (EditText)findViewById(R.id.txt_LastName);
        et_user = (EditText)findViewById(R.id.txt_Usuario);
        et_Email = (EditText)findViewById(R.id.txt_Email);
        et_Password = (EditText)findViewById(R.id.txt_password);
        et_Password2 = (EditText)findViewById(R.id.txt_confirmarPassword);
        et_rol = findViewById(R.id.type);

    }
    public void RegistrarButton(View view){
        DataBase datos = new DataBase(this, "baseDatos", null, 1);
        SQLiteDatabase BaseDeDatos = datos.getWritableDatabase();

        String rol = String.valueOf(et_rol.getSelectedItem());
        String firstname = et_Name.getText().toString();
        String lastname = et_LastName.getText().toString();
        String user = et_user.getText().toString();
        String email = et_Email.getText().toString();
        String password = et_Password.getText().toString();
        String password2 = et_Password2.getText().toString();


        if(!firstname.isEmpty() && !lastname.isEmpty() && !user.isEmpty() && !email.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("Rol", rol);
            registro.put("Nombre", firstname);
            registro.put("Apellido", lastname);
            registro.put("Usuario", user);
            registro.put("Correo", email);
            registro.put("Contraseña", password);
            registro.put("Confirmar Contraseña", password2);


            BaseDeDatos.insert("usuarios", null, registro);

            BaseDeDatos.close();
            et_Name.setText("");
            et_LastName.setText("");
            et_user.setText("");
            et_Email.setText("");
            et_Password.setText("");
            et_Password2.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}