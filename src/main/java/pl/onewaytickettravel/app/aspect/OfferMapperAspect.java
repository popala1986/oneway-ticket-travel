package pl.onewaytickettravel.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.onewaytickettravel.app.entities.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class OfferMapperAspect {

    private static final Logger log = LoggerFactory.getLogger(OfferMapperAspect.class);
    /**
     * Logs input data before mapping Offer to OfferDto.
     * Useful for debugging and tracking incoming entity values.
     */

    @Before("execution(* pl.onewaytickettravel.app.mapper.OfferMapper.offerToOfferDto(..)) && args(offer)")
    public void logInput(Offer offer) {
        log.info("Mapping Offer: {} | City: {}", offer.getName(),
                offer.getCity() != null ? offer.getCity().getName() : "no city");
    }

    /**
     * Measures execution time of the mapping method.
     * Helps identify performance bottlenecks in DTO conversion.
     */

    @Around("execution(* pl.onewaytickettravel.app.mapper.OfferMapper.offerToOfferDto(..))")
    public Object measureMappingTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long duration = System.nanoTime() - start;
        log.info("Mapping took {} µs", duration / 1000);
        return result;
    }

    /**
     * Logs any exceptions thrown during the mapping process.
     * Ensures visibility of mapping failures in logs.
     */

    @AfterThrowing(pointcut = "execution(* pl.onewaytickettravel.app.mapper.OfferMapper.offerToOfferDto(..))",
            throwing = "ex")
    public void logMappingError(Exception ex) {
        log.error("Error during DTO mapping: {}", ex.getMessage(), ex);
    }
}