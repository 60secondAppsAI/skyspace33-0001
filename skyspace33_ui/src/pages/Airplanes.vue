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
            <airplane-table
            v-if="airplanes && airplanes.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:airplanes="airplanes"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-airplanes="getAllAirplanes"
             >

            </airplane-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AirplaneTable from "@/components/AirplaneTable";
import AirplaneService from "../services/AirplaneService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AirplaneTable,
  },
  data() {
    return {
      airplanes: [],
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
    async getAllAirplanes(sortBy='airplaneId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AirplaneService.getAllAirplanes(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.airplanes.length) {
					this.airplanes = response.data.airplanes;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching airplanes:", error);
        }
        
      } catch (error) {
        console.error("Error fetching airplane details:", error);
      }
    },
  },
  mounted() {
    this.getAllAirplanes();
  },
  created() {
    this.$root.$on('searchQueryForAirplanesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAirplanes();
    })
  }
};
</script>
<style></style>
