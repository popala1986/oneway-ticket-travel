package pl.onewaytickettravel.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.onewaytickettravel.app.model.SearchFilter;

@Aspect
@Component
public class OfferServiceAspect {

    private static final Logger log = LoggerFactory.getLogger(OfferServiceAspect.class);

    /**
     * Logs input parameters before executing searchOffers.
     */

    @Before("execution(* pl.onewaytickettravel.app.service.OfferService.searchOffers(..)) && args(filter,..)")
    public void logSearchInput(SearchFilter filter) {
        log.info("🔍 SearchFilter received: continent={}, country={}, city={}",
                filter.getContinentName(),
                filter.getCountryName(),
                filter.getCityName());

    }

    /**
     * Measures execution time of searchOffers method.
     */

    @Around("execution(* pl.onewaytickettravel.app.service.OfferService.searchOffers(..))")
    public Object measureSearchTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long duration = System.nanoTime() - start;
        log.info("⏱️ searchOffers executed in {} µs", duration / 1000);
        return result;
    }

    /**
     * Logs any exceptions thrown during searchOffers execution.
     */

    @AfterThrowing(pointcut = "execution(* pl.onewaytickettravel.app.service.OfferService.searchOffers(..))",
            throwing = "ex")
    public void logSearchError(Exception ex) {
        log.error("❌ Exception in searchOffers: {}", ex.getMessage(), ex);
    }
}