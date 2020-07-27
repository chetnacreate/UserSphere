'use strict';


module.exports = function(){
    return{
        SignUpValidation: ( req, res, next) =>{
            req.checkBody('username' , 'Username is Required').notEmpty()
            req.checkBody('username' , 'Username Must Not be Less Than 5').isLength( { min: 5})
            
            req.checkBody('email' , 'Email is Required').notEmpty()
            req.checkBody('email' , 'Email is Invalid').isEmail()
            
            req.checkBody('password' , 'password is Required').notEmpty()
            req.checkBody('password' , 'password Must Not be Less Than 5').isLength( { min: 5})
           
           
            req.getValidationResult()
            .then((result) =>{
                const errors = result.array()
                const messages = []
                errors.forEach((error) => {
                  messages.push(error.msg)  
                })
                
                req.flash('error' , messages)
                req.redirect('/signup')
            })
            
            .catch((err) =>{
                return next()
            })
        },
        
        
          LoginValidation: ( req, res, next) =>{
            req.checkBody('email' , 'Email is Required').notEmpty()
            req.checkBody('email' , 'Email is Invalid').isEmail()
            
            req.checkBody('password' , 'password is Required').notEmpty()
            req.checkBody('password' , 'password Must Not be Less Than 5').isLength( { min: 5})
           
           
            req.getValidationResult()
            .then((result) =>{
                const errors = result.array()
                const messages = []
                errors.forEach((error) => {
                  messages.push(error.msg)  
                })
                
                req.flash('error' , messages)
                req.redirect('/')
            })
            
            .catch((err) =>{
                return next()
            })
        }
    
    }
}