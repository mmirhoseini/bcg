package com.mirhoseini.bcg.test.support;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowProgressDialog;
import org.robolectric.shadows.ShadowToast;

import static com.mirhoseini.bcg.test.support.ResourceLocator.getString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Mohsen on 08/01/2017.
 */

public class Assert {

    public static void assertViewIsVisible(View view) {
        assertNotNull(view);
        assertThat(view.getVisibility(), equalTo(View.VISIBLE));
    }

    public static void assertViewIsNotVisible(View view) {
        assertNotNull(view);
        assertThat(view.getVisibility(), not(equalTo(View.VISIBLE)));
    }

    public static void assertAlertDialogIsShown(@StringRes int title, @StringRes int message) {
        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(alert);
        assertThat(shadowAlertDialog.getTitle().toString(), equalTo(getString(title)));
        assertThat(shadowAlertDialog.getMessage().toString(), equalTo(getString(message)));
    }

    public static void assertProgressDialogIsShown(@StringRes int title) {
        ProgressDialog alert = (ProgressDialog) ShadowProgressDialog.getLatestAlertDialog();
        ShadowProgressDialog shadowProgressDialog = shadowOf(alert);
        assertThat(shadowProgressDialog.getTitle().toString(), equalTo(getString(title)));
    }

    public static void assertSnackbarIsShown(@StringRes int message) {
        Snackbar snackbar = ShadowSnackbar.getLatestSnackbar();
        ShadowSnackbar shadowSnackbar = ShadowSnackbar.shadowOf(snackbar);
        assertThat(shadowSnackbar.text, equalTo(getString(message)));

    }

    public static void assertToastIsShown(String message) {
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo(message));
    }

}
