import http from "../http-common"; 

class CityService {
  getAllCitys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/city/citys`, searchDTO);
  }

  get(cityId) {
    return this.getRequest(`/city/${cityId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/city?field=${matchData}`, null);
  }

  addCity(data) {
    return http.post("/city/addCity", data);
  }

  update(data) {
  	return http.post("/city/updateCity", data);
  }
  
  uploadImage(data,cityId) {
  	return http.postForm("/city/uploadImage/"+cityId, data);
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

export default new CityService();
