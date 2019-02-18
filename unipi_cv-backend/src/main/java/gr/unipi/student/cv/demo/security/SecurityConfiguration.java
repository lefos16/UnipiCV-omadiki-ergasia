package gr.unipi.student.cv.demo.security;

import gr.unipi.student.cv.demo.security.JwtToken.JwtAuthenticationEntryPoint;
import gr.unipi.student.cv.demo.security.JwtToken.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;



    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .userDetailsService(this.userDetailsService)
//                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                // ignoring the "/", "/index.html", "/app/**", "/register",
                // "/favicon.ico"
                .antMatchers("/swagger-ui.html", "/webjars/springfox-swagger-ui/css/typography.css",
                        "/webjars/springfox-swagger-ui/css/reset.css","/webjars/springfox-swagger-ui/css/screen.css",
                        "/webjars/springfox-swagger-ui/lib/object-assign-pollyfill.js", "/webjars/springfox-swagger-ui/lib/jquery-1.8.0.min.js",
                        "/webjars/springfox-swagger-ui/lib/jquery.slideto.min.js", "/webjars/springfox-swagger-ui/lib/jquery.wiggle.min.js",
                        "/webjars/springfox-swagger-ui/lib/jquery.ba-bbq.min.js", "/webjars/springfox-swagger-ui/lib/handlebars-4.0.5.js",
                        "/webjars/springfox-swagger-ui/lib/lodash.min.js", "/webjars/springfox-swagger-ui/lib/backbone-min.js",
                        "/webjars/springfox-swagger-ui/swagger-ui.min.js", "/webjars/springfox-swagger-ui/lib/highlight.9.1.0.pack.js",
                        "/webjars/springfox-swagger-ui/lib/highlight.9.1.0.pack_extended.js", "/webjars/springfox-swagger-ui/lib/jsoneditor.min.js",
                        "/webjars/springfox-swagger-ui/lib/marked.js", "/webjars/springfox-swagger-ui/lib/swagger-oauth.js",
                        "/webjars/springfox-swagger-ui/springfox.js", "/webjars/springfox-swagger-ui/images/logo_small.png",
                        "/webjars/springfox-swagger-ui/css/print.css", "/webjars/springfox-swagger-ui/lib/jquery-1.8.0.min.js",
                        "/webjars/springfox-swagger-ui/lib/jquery.slideto.min.js", "/webjars/springfox-swagger-ui/lib/jquery.wiggle.min.js",
                        "/webjars/springfox-swagger-ui/lib/jquery.ba-bbq.min.js", "/webjars/springfox-swagger-ui/lib/handlebars-4.0.5.js",
                        "/webjars/springfox-swagger-ui/lib/lodash.min.js", "/webjars/springfox-swagger-ui/lib/backbone-min.js",
                        "/webjars/springfox-swagger-ui/swagger-ui.min.js", "/webjars/springfox-swagger-ui/lib/highlight.9.1.0.pack.js",
                        "/webjars/springfox-swagger-ui/lib/highlight.9.1.0.pack_extended.js", "/webjars/springfox-swagger-ui/lib/jsoneditor.min.js",
                        "webjars/springfox-swagger-ui/lib/marked.js", "/webjars/springfox-swagger-ui/lib/swagger-oauth.js",
                        "/webjars/springfox-swagger-ui/springfox.js", "/webjars/springfox-swagger-ui/images/logo_small.png",
                        "/webjars/springfox-swagger-ui/images/favicon-16x16.png","/webjars/springfox-swagger-ui/fonts/DroidSans-Bold.ttf",
                        "/webjars/springfox-swagger-ui/fonts/DroidSans.ttf", "/swagger-resources/configuration/ui", "/swagger-resources",
                        "/v2/api-docs", "/swagger-resources/configuration/security",  "/reset.css", "/favicon.ico");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .authorizeRequests().antMatchers("/domain/**").authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/**").permitAll()
                .authorizeRequests()
                .antMatchers("/","/login/**","/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                         "/createUser",
                         "/deleteUser",
                        "/search",
                        "/getUsers",
                        "/file/cv",
                        "/getFile",
                         "/webjars/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class);
        ;


        // Custom JWT based security filter
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }


    public class WebSecurityCorsFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request= (HttpServletRequest) servletRequest;

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "*");
            chain.doFilter(servletRequest, servletResponse);
        }
        @Override
        public void destroy() {
        }
    }

}
