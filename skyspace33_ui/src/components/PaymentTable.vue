
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Payments</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalPayments = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalPayments">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Payment</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="PaymentId" type="text" placeholder="Enter PaymentId" v-model="paymentToAdd.paymentId"></base-input>
  <base-input label="Amount" type="text" placeholder="Enter Amount" v-model="paymentToAdd.amount"></base-input>
  <base-input label="PaymentDate" type="text" placeholder="Enter PaymentDate" v-model="paymentToAdd.paymentDate"></base-input>
  <base-input label="PaymentMethod" type="text" placeholder="Enter PaymentMethod" v-model="paymentToAdd.paymentMethod"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="payments" :row-key="record => record.PaymentId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <PaymentPictureView :payments="payments" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import PaymentService from "../services/PaymentService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import PaymentPictureView from './PaymentPictureView.vue';


const paymentsColumns = [
  "paymentId",
  "year",
  "date",
  "competitionId",
  "paymentId"
]

export default {
  props: {
    payments: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    PaymentPictureView
  },

  data() {
    return {
      modalPayments: false,
        isTableView: true,

      columns: [
        {
          title: 'Payment Id',
		dataIndex: 'paymentId',
          visible: true,
          scopedSlots: { customRender: 'paymentId' },
          sorter: true
          //sorter: (a, b) => a.paymentId - b.paymentId,
          //sorter: (a, b) => a.paymentId.localeCompare(b.paymentId),
        },
        {
          title: 'Amount',
		dataIndex: 'amount',
          visible: true,
          scopedSlots: { customRender: 'amount' },
          sorter: true
          //sorter: (a, b) => a.amount - b.amount,
          //sorter: (a, b) => a.amount.localeCompare(b.amount),
        },
        {
          title: 'Payment Date',
		dataIndex: 'paymentDate',
          visible: true,
          scopedSlots: { customRender: 'paymentDate' },
          sorter: true
          //sorter: (a, b) => a.paymentDate - b.paymentDate,
          //sorter: (a, b) => a.paymentDate.localeCompare(b.paymentDate),
        },
        {
          title: 'Payment Method',
		dataIndex: 'paymentMethod',
          visible: true,
          scopedSlots: { customRender: 'paymentMethod' },
          sorter: true
          //sorter: (a, b) => a.paymentMethod - b.paymentMethod,
          //sorter: (a, b) => a.paymentMethod.localeCompare(b.paymentMethod),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} payments`,
      },

      payments: [],
      paymentToAdd: {},

      paymentsTable: {
        columns: [...paymentsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'paymentId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderPaymentsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let paymentsTableData = [];
      for (let i = 0; i < this.payments.length; i++) {
        paymentsTableData.push({
          id: i,
          paymentId: this.payments[i].paymentId,
          year: this.payments[i].year,
          date: this.payments[i].date,
          competitionId: this.payments[i].competitionId,
          paymentId: this.payments[i].paymentId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-payments',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToAirplaneDetail(id) {
      this.$router.push({ name: 'AirplaneDetail', params: { airplaneId: id.toString() }})
    },
    routingToAirlineDetail(id) {
      this.$router.push({ name: 'AirlineDetail', params: { airlineId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToBaggageDetail(id) {
      this.$router.push({ name: 'BaggageDetail', params: { baggageId: id.toString() }})
    },
    routingToDestinationDetail(id) {
      this.$router.push({ name: 'DestinationDetail', params: { destinationId: id.toString() }})
    },
    routingToCityDetail(id) {
      this.$router.push({ name: 'CityDetail', params: { cityId: id.toString() }})
    },
    routingToRouteDetail(id) {
      this.$router.push({ name: 'RouteDetail', params: { routeId: id.toString() }})
    },
    routingToCrewDetail(id) {
      this.$router.push({ name: 'CrewDetail', params: { crewId: id.toString() }})
    },
    routingToPilotDetail(id) {
      this.$router.push({ name: 'PilotDetail', params: { pilotId: id.toString() }})
    },
    routingToRuleDetail(id) {
      this.$router.push({ name: 'RuleDetail', params: { ruleId: id.toString() }})
    },
    routingToCheckinDetail(id) {
      this.$router.push({ name: 'CheckinDetail', params: { checkinId: id.toString() }})
    },
    routingToLoyaltyProgramDetail(id) {
      this.$router.push({ name: 'LoyaltyProgramDetail', params: { loyaltyProgramId: id.toString() }})
    },
    routingToMembershipDetail(id) {
      this.$router.push({ name: 'MembershipDetail', params: { membershipId: id.toString() }})
    },
    routingToReviewDetail(id) {
      this.$router.push({ name: 'ReviewDetail', params: { reviewId: id.toString() }})
    },
    routingToNotificationDetail(id) {
      this.$router.push({ name: 'NotificationDetail', params: { notificationId: id.toString() }})
    },
    routingToPromotionDetail(id) {
      this.$router.push({ name: 'PromotionDetail', params: { promotionId: id.toString() }})
    },
    routingToFeedbackDetail(id) {
      this.$router.push({ name: 'FeedbackDetail', params: { feedbackId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForPaymentsChanged', this.searchQuery);
		//this.renderPaymentsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalPayments = false;

      const currentDate = new Date().getTime();
      this.paymentToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.paymentToAdd);
      console.log(jsonData);
      
      const res = await PaymentService.addPayment(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderPaymentsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
