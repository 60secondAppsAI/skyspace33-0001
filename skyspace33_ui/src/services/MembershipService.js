import http from "../http-common"; 

class MembershipService {
  getAllMemberships(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/membership/memberships`, searchDTO);
  }

  get(membershipId) {
    return this.getRequest(`/membership/${membershipId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/membership?field=${matchData}`, null);
  }

  addMembership(data) {
    return http.post("/membership/addMembership", data);
  }

  update(data) {
  	return http.post("/membership/updateMembership", data);
  }
  
  uploadImage(data,membershipId) {
  	return http.postForm("/membership/uploadImage/"+membershipId, data);
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

export default new MembershipService();
