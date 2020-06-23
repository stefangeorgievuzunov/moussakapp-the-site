package web.models.response;

public class AjaxResponse {
    private Boolean success;
    private String error;
    private String url;

    public AjaxResponse(){
        success=false;
        error="";
        url ="";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
