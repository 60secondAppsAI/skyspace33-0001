import http from "../http-common"; 

class RouteService {
  getAllRoutes(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/route/routes`, searchDTO);
  }

  get(routeId) {
    return this.getRequest(`/route/${routeId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/route?field=${matchData}`, null);
  }

  addRoute(data) {
    return http.post("/route/addRoute", data);
  }

  update(data) {
  	return http.post("/route/updateRoute", data);
  }
  
  uploadImage(data,routeId) {
  	return http.postForm("/route/uploadImage/"+routeId, data);
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

export default new RouteService();
