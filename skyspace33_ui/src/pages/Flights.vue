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
            <flight-table
            v-if="flights && flights.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:flights="flights"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-flights="getAllFlights"
             >

            </flight-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FlightTable from "@/components/FlightTable";
import FlightService from "../services/FlightService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FlightTable,
  },
  data() {
    return {
      flights: [],
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
    async getAllFlights(sortBy='flightId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FlightService.getAllFlights(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.flights.length) {
					this.flights = response.data.flights;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching flights:", error);
        }
        
      } catch (error) {
        console.error("Error fetching flight details:", error);
      }
    },
  },
  mounted() {
    this.getAllFlights();
  },
  created() {
    this.$root.$on('searchQueryForFlightsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFlights();
    })
  }
};
</script>
<style></style>
