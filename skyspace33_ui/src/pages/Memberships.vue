<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <membership-table
            v-if="memberships && memberships.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:memberships="memberships"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-memberships="getAllMemberships"
             >

            </membership-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MembershipTable from "@/components/MembershipTable";
import MembershipService from "../services/MembershipService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MembershipTable,
  },
  data() {
    return {
      memberships: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllMemberships(sortBy='membershipId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MembershipService.getAllMemberships(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.memberships.length) {
					this.memberships = response.data.memberships;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching memberships:", error);
        }
        
      } catch (error) {
        console.error("Error fetching membership details:", error);
      }
    },
  },
  mounted() {
    this.getAllMemberships();
  },
  created() {
    this.$root.$on('searchQueryForMembershipsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMemberships();
    })
  }
};
</script>
<style></style>
