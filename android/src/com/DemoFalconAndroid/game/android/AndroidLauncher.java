package com.DemoFalconAndroid.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.DemoFalconAndroid.game.DemoGame;
import com.newrelic.agent.android.NewRelic;
import com.splunk.mint.Mint;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
        Mint.initAndStartSession(AndroidLauncher.this, "f9e23a45");

        NewRelic.withApplicationToken(

                "AAbdec3c31fb037c296fe10a86ab9849d0dee61e19"
        ).start(this.getApplication());

		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new DemoGame(), config);
	}
}
