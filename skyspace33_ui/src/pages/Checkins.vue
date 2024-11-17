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
            <checkin-table
            v-if="checkins && checkins.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:checkins="checkins"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-checkins="getAllCheckins"
             >

            </checkin-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CheckinTable from "@/components/CheckinTable";
import CheckinService from "../services/CheckinService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CheckinTable,
  },
  data() {
    return {
      checkins: [],
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
    async getAllCheckins(sortBy='checkinId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CheckinService.getAllCheckins(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.checkins.length) {
					this.checkins = response.data.checkins;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching checkins:", error);
        }
        
      } catch (error) {
        console.error("Error fetching checkin details:", error);
      }
    },
  },
  mounted() {
    this.getAllCheckins();
  },
  created() {
    this.$root.$on('searchQueryForCheckinsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCheckins();
    })
  }
};
</script>
<style></style>
