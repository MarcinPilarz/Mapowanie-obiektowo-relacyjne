using Microsoft.AspNetCore.Mvc;
using Insurance1;
using Insurance1.Dtos;
using System.Net.Http;
using System.Net.Http.Json;
using Microsoft.EntityFrameworkCore;

namespace Insurance1.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class PlatnosciController : ControllerBase
    {
        private readonly UbezpieczeniaContext _db;

        public PlatnosciController(UbezpieczeniaContext db)
        {
            _db = db;
        }

        
        [HttpPost]
        public IActionResult CreatePayment([FromBody] PaymentRequestDto dto)
        {
            
            var platnosc = new Platnosci
            {
                Nazwa = dto.Description,
                Kwota = dto.Amount,
                Data = DateTime.Now,
               
                ZewnetrzneId = (int)dto.VisitId,
                StatusPlatnosciId = 1 
            };

            _db.Platnoscis.Add(platnosc);
            try
            {
                _db.SaveChanges();
            }
            catch (DbUpdateException ex)
            {
                
                var realCause = ex.InnerException?.Message;
                Console.WriteLine(realCause);
                throw; 
            }


            
            return Ok(platnosc.Id);
        }

        
        [HttpPut("confirm/{id}")]
        public async Task<IActionResult> ConfirmPayment(int id)
        {
            var platnosc = await _db.Platnoscis.FindAsync(id);
            if (platnosc == null)
                return NotFound("Nie znaleziono płatności o podanym ID");

            
            platnosc.StatusPlatnosciId = 2; // np. 2 = "opłacona"
            platnosc.Data = DateTime.Now;
            await _db.SaveChangesAsync();

            
            if (platnosc.ZewnetrzneId > 0)
            {
                using var httpClient = new HttpClient();
                var springUrl = $"http://localhost:8080/api/visits/{platnosc.ZewnetrzneId}/confirmPayment";

                
                var response = await httpClient.PutAsJsonAsync(springUrl, new { });

                if (!response.IsSuccessStatusCode)
                {
                    return StatusCode((int)response.StatusCode,
                        "Błąd przy wywołaniu Spring: " + await response.Content.ReadAsStringAsync());
                }
            }

            return Ok("Płatność potwierdzona, wizyta zaktualizowana w Springu.");
        }
    }
}
