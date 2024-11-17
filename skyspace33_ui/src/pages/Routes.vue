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
            <route-table
            v-if="routes && routes.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:routes="routes"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-routes="getAllRoutes"
             >

            </route-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RouteTable from "@/components/RouteTable";
import RouteService from "../services/RouteService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RouteTable,
  },
  data() {
    return {
      routes: [],
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
    async getAllRoutes(sortBy='routeId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RouteService.getAllRoutes(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.routes.length) {
					this.routes = response.data.routes;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching routes:", error);
        }
        
      } catch (error) {
        console.error("Error fetching route details:", error);
      }
    },
  },
  mounted() {
    this.getAllRoutes();
  },
  created() {
    this.$root.$on('searchQueryForRoutesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRoutes();
    })
  }
};
</script>
<style></style>
