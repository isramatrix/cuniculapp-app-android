package upv.cuniculappteam.cuniculapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.logic.firebase.Firebase;

public class SplashActivity extends AppCompatActivity {

    private static final int MIN_SPLASH_TIME = 1200;

    private static final int MIN_LOADING_TIME = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread initializeThread = new InitializeThread();
        Thread delay = new DelayingThread(initializeThread);

        initializeThread.start();
        delay.start();
    }

    class InitializeThread extends Thread
    {
        @Override
        public void run() {
            Firebase.initialize(SplashActivity.this);
        }
    }

    class DelayingThread extends Thread
    {
        private final Thread thread;

        DelayingThread(Thread thread) { this.thread = thread; }

        @Override
        public void run()
        {
            try {
                // Se espera un tiempo de simulación de carga de datos.
                sleep(MIN_LOADING_TIME);

                // Muesta la animación de carga de datos.
                runOnUiThread(() -> findViewById(R.id.splash_progress).setVisibility(View.VISIBLE));

                sleep(MIN_SPLASH_TIME - MIN_LOADING_TIME);

                // Se espera a que realmente termine la carga de datos.
                thread.join();
            }
            catch (InterruptedException ignored) { }

            // Inicializa la primera actividad.
            finally { switchActivity(); }
        }
    }

    private void switchActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
