package br.com.projecao.projetogym.api.user;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getuserRole(){
        return role;
    }

}
