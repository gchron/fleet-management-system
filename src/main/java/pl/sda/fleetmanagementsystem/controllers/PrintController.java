package pl.sda.fleetmanagementsystem.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.PaymentDto;
import pl.sda.fleetmanagementsystem.service.PaymentFinder;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequiredArgsConstructor
public class PrintController {
    @Autowired
    private final ServletContext servletContext;

    private final PaymentFinder paymentFinder;



    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/{id}/createPDF")
    public void createPDF(@PathVariable Integer id) throws IOException, DocumentException {
        PaymentDto paymentDto = paymentFinder.findById(id);
        Document document = new Document();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("/newPayment.pdf")));
        PdfWriter.getInstance(document, bufferedOutputStream);


        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk driverUsername = new Chunk(paymentDto.getDriverdto().getUserDto().getUserName(), font);
        Chunk value = new Chunk(paymentDto.getValue().toString(), font);
        document.add(driverUsername);
        document.add(value);
        document.close();

        ModelAndView modelAndView = new ModelAndView("/print.html");
        modelAndView.addObject("payment", document);






    }

    @GetMapping("/print")
    public ResponseEntity<String> print(){



        return new ResponseEntity<>("Hello", HttpStatus.OK);


    }
}
