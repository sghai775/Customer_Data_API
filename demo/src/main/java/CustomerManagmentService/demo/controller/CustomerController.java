package CustomerManagmentService.demo.controller;

import CustomerManagmentService.demo.exception.ResourceNotFoundException;
import CustomerManagmentService.demo.model.Customer;
import CustomerManagmentService.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // create customer
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // get all customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    // get customer by id
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with id : " + id));
        return ResponseEntity.ok(customer);
    }

    // update customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with id : " + id));

        //updates different fields based on what data was sent in the http request
        if(customerDetails.getName() != null) {
            customer.setName(customerDetails.getName());
        }

        if(customerDetails.getEmail() != null) {
            customer.setEmail(customerDetails.getEmail());
        }

        if(customerDetails.getPhone() != 0) {
            customer.setPhone(customerDetails.getPhone());
        }

        if(customerDetails.getZip() != 0) {
            customer.setZip(customerDetails.getZip());
        }

        if(customerDetails.getMembership() != customer.getMembership()) {
            customer.setMembership(customerDetails.getMembership());
        }

        Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // delete customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with id : " + id));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
