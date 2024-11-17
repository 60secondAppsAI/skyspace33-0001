import http from "../http-common"; 

class BookingService {
  getAllBookings(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/booking/bookings`, searchDTO);
  }

  get(bookingId) {
    return this.getRequest(`/booking/${bookingId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/booking?field=${matchData}`, null);
  }

  addBooking(data) {
    return http.post("/booking/addBooking", data);
  }

  update(data) {
  	return http.post("/booking/updateBooking", data);
  }
  
  uploadImage(data,bookingId) {
  	return http.postForm("/booking/uploadImage/"+bookingId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new BookingService();
