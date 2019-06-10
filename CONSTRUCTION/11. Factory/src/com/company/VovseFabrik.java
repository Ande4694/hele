package com.company;

public class VovseFabrik {

    public static Dog getDog(String criteria){
        if (criteria.equals("lille")){
            return new Poodle();
        }
        if (criteria.equals("mellem")){
            return new Labrador();
        }
        if (criteria.equals("stor")){
            return new Bernard();
        }
        return null;
    }
}
