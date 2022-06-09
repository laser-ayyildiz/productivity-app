package tr.edu.ege.apigateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tr.edu.ege.apigateway.security.JwtUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends ZuulFilter {
    private final JwtUtils jwtUtils;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        final String jwtToken = ctx.getRequest().getHeader("Authorization").split("Bearer ")[1];
        final Long userId = jwtUtils.getUserIdJwtToken(jwtToken);
        ctx.addZuulRequestHeader("userId", String.valueOf(userId));
        return null;
    }
}
