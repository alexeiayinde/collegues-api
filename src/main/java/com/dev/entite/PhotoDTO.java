package com.dev.entite;

public class PhotoDTO {

    private String matricule;
    private String photoUrl;

    public PhotoDTO() {
    }

    public PhotoDTO(String matricule, String photoUrl) {
        this.matricule = matricule;
        this.photoUrl = photoUrl;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("photoDTO [matricule=");
        builder.append(matricule);
        builder.append(", photoUrl=");
        builder.append(photoUrl);
        builder.append("]");
        return builder.toString();
    }

}
