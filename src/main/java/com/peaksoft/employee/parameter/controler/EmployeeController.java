package com.peaksoft.employee.parameter.controler;

import com.peaksoft.employee.parameter.model.Employee;
import com.peaksoft.employee.parameter.service.CountryService;
import com.peaksoft.employee.parameter.service.EmployeeService;
import com.peaksoft.employee.parameter.service.LocationService;
import com.peaksoft.employee.parameter.service.StateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

	private final UserDetailsService userDetailsService;
	private final EmployeeService employeeService;
	private final StateService stateService;
	private final CountryService countryService;
	private final LocationService locationService;

	@GetMapping("/employee")
	public String employee(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		return"parameter/employee/index";
	}

	@GetMapping("/employees")
	public String listOfEmployee(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("listOfEmployee", employeeService.listOfEmployee());
		return"/parameter/employee/listOfEmployee";
	}

	@GetMapping("/addEmployee")
	public String addEmployee(Model model,  Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("createEmployee", new Employee());
		model.addAttribute("listOfCountry", countryService.listOfCountry());
		model.addAttribute("listOfState", stateService.listOfState());
		model.addAttribute("listOfLocation", locationService.listOfLocation());
		return"/parameter/employee/addEmployee";
	}

	@PostMapping("/addEmployee")
	public String saveEmployee(@ModelAttribute("createEmployee")
								   @Valid Employee employee, BindingResult result){
		if (result.hasErrors()) {
			return"/parameter/employee/addEmployee";
		}
		employeeService.saveEmployee(employee);
		return"redirect:/employees";
	}

	@GetMapping("/employees/{id}/edit")
	public String editEmployee(@PathVariable Integer id, Model model,  Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("editEmployee", employeeService.findEmployeeById(id));
		model.addAttribute("listOfCountry", countryService.listOfCountry());
		model.addAttribute("listOfState", stateService.listOfState());
		model.addAttribute("listOfLocation", locationService.listOfLocation());
		return "/parameter/employee/editEmployee";
	}

	@PostMapping("/employees/{id}/edit")
	public String updateEmployee(@PathVariable Integer id,
								 @ModelAttribute("editEmployee") @Valid Employee employee,
								 BindingResult result) {

		if (result.hasErrors()) {
			return"/parameter/employee/editEmployee";
		}
		Employee emp = employeeService.findEmployeeById(id);// EKLENTİLER GELİR BURAYA
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setOccupation(employee.getOccupation());
		emp.setPhone(employee.getPhone());
		emp.setEmail(employee.getEmail());
		emp.setCountry(employee.getCountry());
		emp.setState(employee.getState());
		emp.setLocation(employee.getLocation());
		emp.setGender(employee.getGender());
		emp.setDateOfBirth(employee.getDateOfBirth());
		emp.setMaritalStatus(employee.getMaritalStatus());
		emp.setPhoto(employee.getPhoto());
		return "redirect:/employees";
	}

	@GetMapping("/employees/{id}/delete")
	public String deleteEmployee(@PathVariable Integer id){
		Employee employee = employeeService.findEmployeeById(id);
		employeeService.deleteEmployee(employee);
		return"redirect:/employees";
	}

	@GetMapping("/employees/{id}/info")
	public String infoEmployee(Model model, @PathVariable Integer id, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("infoEmployee", employeeService.findEmployeeById(id));
		return"/parameter/employee/infoEmployee";
	}

	@RequestMapping(value="/employees/uploadPhoto", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		File newFile = new File("D:\\SOLUTIONS\\fleetms\\uploads" + file.getOriginalFilename());
		newFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(newFile);
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
	}	

	@PostMapping("/employees/uploadPhoto2")
	public String uploadFile2(@RequestParam("file") MultipartFile file, Principal principal) 
			throws IllegalStateException, IOException {
			String baseDirectory = "D:\\SOLUTIONS\\fleetms\\src\\main\\resources\\static\\img\\photos\\" ;
			file.transferTo(new File(baseDirectory + principal.getName() + ".jpg"));
			return "redirect:/employees";
	}

//	@RequestMapping(value="/employee/profile")
//	public String profile(Model model, Principal principal) {
//		String un = principal.getName();
//		addModelAttributes(model);
//		model.addAttribute("employee", employeeService.findByUsername(un));
//		return "profile";
//	}

}
