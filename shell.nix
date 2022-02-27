{ pkgs ? import <nixpkgs> {} }:
  pkgs.mkShell {

    nativeBuildInputs = [ pkgs.jdk17_headless
                          pkgs.maven 
                          pkgs.heroku
                        ]; 

}
