package web.models.response;

public class RegisterResponse {
    private Boolean success;
    private String redirect;
    private String error;

    public RegisterResponse() {
        success=false;
        redirect="";
        error="";
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
