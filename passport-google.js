'use strict';

const passport = require('passport')
const User = require('../model/user')
const GoogleStrategy = require('passport-google-oauth').OAuth2Strategy
const process = require('../secret/secretFile')


passport.serializeUser((user , done) => {
    done(null,user.id)
})

passport.deserializeUser((id, done) => {
    User.findById(id, (err, user)  => {
        done(err, user)
    })
})

passport.use( new GoogleStrategy({
    clientID: process.google.clientID,
    clientSecret: process.google.clientSecret,

    callbackURL: 'http://localhost:3000/auth/google/callback',
        passReqToCallback: true
        

}, (req, accesstoken, refreshToken, profile , done) => {
    
    User.findOne({goggle: profile.id }, (err, user) => {
        if(err){
            return done(err);
        }
       
        if(user){
            return done(null, user);
        }
        else{
            const newUser = new User();
            newUser.google = profile.id;
            newUser.fullname = profile.displayName;
            newUser.email = profile.emails[0].value;
            newUser.userImage = profile._json.image.url;
            
            newUser.save((err) =>{
                if(err){
                    return done(err);
                }
                return done(null, newUser);
            })
        }
      
    })
}))




