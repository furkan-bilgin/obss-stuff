package furkanbilgin.obssstuff.phoneRegistry;

import furkanbilgin.obssstuff.phoneRegistry.models.PhoneRegistryEntry;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/phone-registry")
public class PhoneNumberRegistryServlet extends HttpServlet {
    private final ArrayList<PhoneRegistryEntry> phoneEntries = new ArrayList<>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String fullNameFilter =  request.getParameter("fullName");
        List<PhoneRegistryEntry> phoneEntriesFiltered = phoneEntries
                .stream()
                .filter(x -> fullNameFilter == null || fullNameFilter.equals(x.getFullName()))
                .collect(Collectors.toList());
        for (PhoneRegistryEntry entry : phoneEntriesFiltered) {
            out.println(entry.toString());
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PhoneRegistryEntry entry = new PhoneRegistryEntry(request.getParameter("fullName"), request.getParameter("phoneNumber"));
        phoneEntries.add(entry);
        PrintWriter out = response.getWriter();
        out.println(entry);
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         Optional<PhoneRegistryEntry> entry = phoneEntries.stream()
                .filter(x -> x.getPhoneNumber()
                .equals(request.getParameter("fullName")))
                .findFirst();
        if (!entry.isPresent()) {
            throw new ServletException("Invalid full name");
        }
        entry.get().setPhoneNumber(request.getParameter("phoneNumber"));
        PrintWriter out = response.getWriter();
        out.println(entry.get());
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean isDeleted = phoneEntries.removeIf(x -> x.getFullName().equals(request.getParameter("fullName")));
        PrintWriter out = response.getWriter();
        out.println(isDeleted);
    }
}
