package com.qp.Assessment.web.rest;

import java.util.LinkedList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.Assessment.domain.Grocery;
import com.qp.Assessment.dto.GroceryRequest;
import com.qp.Assessment.service.GroceryService;

@RestController
@RequestMapping("/api")
public class GroceryResource {

	@Autowired
	private GroceryService groceryService;
	
	
	@PostMapping("/grocery/addItem")
	public ResponseEntity<Grocery> addItem(@RequestBody(required = false)GroceryRequest req){
		Grocery newItem=null;
		try {
			newItem= this.groceryService.createItem(req.getItemName(), req.getItemPrice(),req.getItemQuantity());
		}catch(Exception e) {
			return new ResponseEntity<Grocery>(newItem, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Grocery>(newItem, HttpStatus.OK);
	}
	
	@GetMapping("/grocery/getAllItems")
	public ResponseEntity<List<Grocery>> getAllitems(){
		List<Grocery> allDetails=null;
		try {
			allDetails=this.groceryService.fetchAllDetails();
		}catch(Exception e) {
			return new ResponseEntity<List<Grocery>>(allDetails,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<List<Grocery>>(allDetails,HttpStatus.OK);
	}
	
	@DeleteMapping("/grocery/deleteItems")
	public ResponseEntity<List<Grocery>> deleteitems(@RequestBody List<GroceryRequest> req){
		try {
		for(GroceryRequest item:req) {
			this.groceryService.deleteItem(item.getItemId());
		}
		}catch(Exception e) {
			return new ResponseEntity<List<Grocery>>(HttpStatus.CONFLICT);
		}
		List<Grocery> allDetails=this.groceryService.fetchAllDetails();
		return new ResponseEntity<List<Grocery>>(allDetails,HttpStatus.OK);
	}
	
	@PutMapping("/grocery/updateGroceries")
	public ResponseEntity<List<Grocery>> updateGroceries(@RequestBody List<GroceryRequest> req){
		try {
		for(GroceryRequest grocery:req) {
			this.groceryService.updateGrocery(grocery);
		}
		}catch(Exception e) {
			return new ResponseEntity<List<Grocery>>(HttpStatus.CONFLICT);
		}
		List<Grocery> allDetails=this.groceryService.fetchAllDetails();
		return new ResponseEntity<List<Grocery>>(allDetails,HttpStatus.OK);
	}
	
	
	@GetMapping("/grocery/getAvailableGroceries")
	public ResponseEntity<List<Grocery>> getAvailableGroceries(){
		List<Grocery> allDetails=null;
		try {
		allDetails=this.groceryService.getAvailableItems();
		}catch(Exception e) {
			return new ResponseEntity<List<Grocery>>(allDetails,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<List<Grocery>>(allDetails,HttpStatus.OK);
	}
	
	@PostMapping("/grocery/addItemstoCart")
	public ResponseEntity<List<Grocery>> addItemstoCart(@RequestBody List<GroceryRequest> req){
		List<Grocery> cartItems=new LinkedList<>();
		try {
		for(GroceryRequest item:req) {
			cartItems=this.groceryService.addToCart(item);
		}
		}catch(Exception e) {
			return new ResponseEntity<List<Grocery>>(cartItems,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<List<Grocery>>(cartItems,HttpStatus.OK);
	}
	
	@GetMapping("/grocery")
	public Grocery getGroceryDetails(@PathParam(value = "id") long id) {
		return this.groceryService.getGrocey(id);
	}
}
