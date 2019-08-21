package dk.dtu.mitprojektarkiv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeletePlayer extends AppCompatActivity implements View.OnClickListener {

    // Buttons
    Button returnBtn, deletePlayerBtn;
    EditText idInput;

    // SqlLiteDateBase
    SqlLiteDataBase sqlLiteDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_player);

        // Gradient Animation
        ConstraintLayout constraintLayout = findViewById(R.id.deletePlayer);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        // Buttons
        returnBtn = (Button) findViewById(R.id.returnBtn);
        deletePlayerBtn = (Button) findViewById(R.id.deletePlayerbtn);
        returnBtn.setOnClickListener(this);
        deletePlayerBtn.setOnClickListener(this);

        idInput = (EditText) findViewById(R.id.deleteID);

        // Sql Data base
        sqlLiteDataBase = new SqlLiteDataBase(this);

    }

    @Override
    public void onClick(View view) {
        if (view != null) {

            if (view == returnBtn) {
                Intent i = new Intent(getApplicationContext(), FrontPage.class);
                startActivity(i);
            }
            if (view == deletePlayerBtn) {
                Integer deletedRows = sqlLiteDataBase.deletePlayer(idInput.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(DeletePlayer.this, "Player Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DeletePlayer.this, "Player Does not exist", Toast.LENGTH_LONG).show();

            }

        }
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
