package tn.esprit.examnomprenom.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    //methode qui permet de calculer et afficher dans les logs la duree d'execution de chaque methode appelee
    private long debut;
    private long fin;

    @Before("execution(* tn.esprit.examnomprenom.services.*.*(..))")
    public void logBefore(JoinPoint joinPoint){
        debut = System.currentTimeMillis();
    }

    @After("execution(* tn.esprit.examnomprenom.services.*.*(..))")
    public void logAfter(JoinPoint joinPoint){
        fin = System.currentTimeMillis();
        log.info("Duree d'execution de la methode : "+joinPoint.getSignature().getName()+" est de : "+(fin-debut)+" ms");
    }
}
