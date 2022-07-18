package com.example.rfcba_bankpet;

public class UserRegister {
    String em;
    String user_name;
    String pass_word;
    String confpass;

        public UserRegister(String user_name, String pass_word, String em, String confpass) {
            this.em = em;
            this.user_name = user_name;
            this.pass_word = pass_word;
            this.confpass = confpass;
        }

        public String getEm() {
        return em;
    }

        public String getUser_name() {
            return user_name;
        }

        public String getPass_word() {
            return pass_word;
        }

        public String getConfpass() {
            return confpass;
        }
    }

