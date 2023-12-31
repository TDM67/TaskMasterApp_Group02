// Generated by view binder compiler. Do not edit!
package com.example.taskmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.taskmanager.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityForgetpassBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button bForget;

  @NonNull
  public final ConstraintLayout clLogin;

  @NonNull
  public final EditText etLoginEmail;

  @NonNull
  public final ProgressBar pbLogin;

  @NonNull
  public final TextView tvLogin;

  @NonNull
  public final TextView tvLoginEmailLabel;

  private ActivityForgetpassBinding(@NonNull ConstraintLayout rootView, @NonNull Button bForget,
      @NonNull ConstraintLayout clLogin, @NonNull EditText etLoginEmail,
      @NonNull ProgressBar pbLogin, @NonNull TextView tvLogin,
      @NonNull TextView tvLoginEmailLabel) {
    this.rootView = rootView;
    this.bForget = bForget;
    this.clLogin = clLogin;
    this.etLoginEmail = etLoginEmail;
    this.pbLogin = pbLogin;
    this.tvLogin = tvLogin;
    this.tvLoginEmailLabel = tvLoginEmailLabel;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityForgetpassBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityForgetpassBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_forgetpass, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityForgetpassBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bForget;
      Button bForget = ViewBindings.findChildViewById(rootView, id);
      if (bForget == null) {
        break missingId;
      }

      ConstraintLayout clLogin = (ConstraintLayout) rootView;

      id = R.id.etLoginEmail;
      EditText etLoginEmail = ViewBindings.findChildViewById(rootView, id);
      if (etLoginEmail == null) {
        break missingId;
      }

      id = R.id.pbLogin;
      ProgressBar pbLogin = ViewBindings.findChildViewById(rootView, id);
      if (pbLogin == null) {
        break missingId;
      }

      id = R.id.tvLogin;
      TextView tvLogin = ViewBindings.findChildViewById(rootView, id);
      if (tvLogin == null) {
        break missingId;
      }

      id = R.id.tvLoginEmailLabel;
      TextView tvLoginEmailLabel = ViewBindings.findChildViewById(rootView, id);
      if (tvLoginEmailLabel == null) {
        break missingId;
      }

      return new ActivityForgetpassBinding((ConstraintLayout) rootView, bForget, clLogin,
          etLoginEmail, pbLogin, tvLogin, tvLoginEmailLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
