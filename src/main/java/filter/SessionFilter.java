package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import model.datasrc.MysqlDataSource;

public class SessionFilter implements Filter {

    @Override
    public void destroy() {
        MysqlDataSource.getInstance().getFactory().getCurrentSession().getTransaction().commit();
        MysqlDataSource.getInstance().getFactory().getCurrentSession().close();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MysqlDataSource.getInstance().getFactory().getCurrentSession();
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
