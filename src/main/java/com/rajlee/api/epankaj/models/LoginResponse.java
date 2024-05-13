package com.rajlee.api.epankaj.models;

    public class LoginResponse {
        private String token;
        private Long userId;
        private String userEmail;

        public LoginResponse() {
        }

        public LoginResponse(String token, Long userId, String userEmail) {
            this.token = token;
            this.userId = userId;
            this.userEmail = userEmail;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }
    }

